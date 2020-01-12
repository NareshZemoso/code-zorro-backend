package models

import (
	"bytes"
	"context"
	"fmt"
	"io/ioutil"
	"os"
	"os/exec"
	"path/filepath"
	"strconv"
	"strings"
	"time"
)

type Code struct {
	Source   string
	Language string
}

func (code Code) SaveToFile(resp chan string) {
	var ext string
	switch code.Language {
	case "C":
		ext = ".c"
	case "CPP":
		ext = ".cpp"
	case "PYTH":
		ext = ".py"
	case "JAVA":
		ext = ".java"
	}
	f, err := ioutil.TempFile(".", strconv.FormatInt(time.Now().Unix(), 10)+"*"+ext)
	if err != nil {
		panic(err.Error())
	}
	defer f.Close()
	f.WriteString(code.Source)
	resp <- f.Name()
}
func recovery(out chan string) {
	if r := recover(); r != nil {
		out <- fmt.Sprintf("Error: %s ", r)
	}
}

func (code Code) RunCode(fName string, out chan string, rctx context.Context) {
	defer recovery(out)
	defer os.Remove(fName)
	var x bytes.Buffer
	if code.Language == "C" || code.Language == "CPP" {
		var cFamily string
		execName := strings.TrimSuffix(fName, filepath.Ext(fName))
		defer os.Remove(strings.TrimSuffix(fName, filepath.Ext(fName)))
		if code.Language == "C" {
			cFamily = "gcc"
		} else {
			cFamily = "g++"
		}
		start := time.Now()
		cmd1 := exec.Command(cFamily, fName, "-o", execName)
		err1 := cmd1.Run()
		if err1 != nil {
			panic(err1.Error())
		}
		ctx, cancel := context.WithTimeout(context.Background(), time.Minute*2)
		defer cancel()
		cmd := exec.CommandContext(ctx, "./"+execName)

		cmd.Stdout = &x
		if err := cmd.Start(); err != nil {
			if cmd.Process != nil {
				cmd.Process.Kill()
			}
			panic(err.Error())
		}
		err2 := cmd.Wait()
		if err2 != nil {
			panic(err2.Error())
		}
		fmt.Println(time.Since(start))
	}

	if code.Language == "JAVA" {
		cmd1 := exec.Command("javac", fName)
		_, err1 := cmd1.Output()
		if err1 != nil {
			panic(err1.Error())
		} else {
			ctx, cancel := context.WithTimeout(context.Background(), 2*time.Second)
			defer cancel()
			cmd := exec.CommandContext(ctx, "java", strings.TrimSuffix(fName, filepath.Ext(fName)))
			cmd.Stdout = &x
			err := cmd.Run()
			if err != nil {
				panic(err.Error())
			}
		}
	}
	if code.Language == "PYTH" {
		ctx, cancel := context.WithTimeout(context.Background(), 5*time.Second)
		defer cancel()
		cmd := exec.CommandContext(ctx, "python3", fName)
		cmd.Stdout = &x
		err := cmd.Run()
		if err != nil {
			panic(err.Error())
		}
	}
	out <- x.String()

}
