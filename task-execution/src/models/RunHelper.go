package models

import (
	"bytes"
	"context"
	"os"
	"os/exec"
	"path/filepath"
	"strings"
	"time"
)

func RunSpecificCode(code *Code,fname string,outChannel *chan string) (string,bool){
	var resp string
	retCode:=true

	if code.Language == "C" || code.Language=="CPP" {
		var cFamily string
		execName:=strings.TrimSuffix(fname, filepath.Ext(fname))
		defer os.Remove(execName)
		if code.Language=="C"{
			cFamily="gcc"
		} else {
			cFamily = "g++"
		}
		cmd1:=exec.Command(cFamily, fname, "-o", execName)
		_,err1:=cmd1.Output()
		if err1 != nil{
			resp = err1.Error()
			retCode=false
		} else {
			ctx,cancel:=context.WithTimeout(context.Background(),2*time.Second)
			defer cancel()
			cmd := exec.CommandContext(ctx,execName)
			var out bytes.Buffer
			cmd.Stdout = &out
			err := cmd.Run()
			if err != nil {
				resp=err.Error()
				retCode=false
			} else {
				resp = out.String()
			}
		}
	}
	if code.Language == "JAVA" {

		cmd1:=exec.Command("javac", fname)
		_,err1:=cmd1.Output()
		if err1 != nil{
			resp = err1.Error()
			retCode=false
		}else {
			ctx,cancel:=context.WithTimeout(context.Background(),2*time.Second)
			defer cancel()
			cmd := exec.CommandContext(ctx,"java", strings.TrimSuffix(fname, filepath.Ext(fname)))
			var out bytes.Buffer
			cmd.Stdout = &out
			err := cmd.Run()
			if err != nil {
				retCode = false
				resp=err.Error()
			}
			resp = out.String()
		}
	}
	if code.Language == "PYTH" {
		ctx,cancel:=context.WithTimeout(context.Background(),5*time.Second)
		defer cancel()
		cmd := exec.CommandContext(ctx,"python3",fname)
		var out bytes.Buffer
		cmd.Stdout = &out
		err := cmd.Run()
		if err != nil {
			resp=err.Error()
			retCode = false
		}
		resp=out.String()
	}

	*outChannel<-resp
	return resp,retCode
}
