package main

import (
	"controllers"
	"fmt"
	"net/http"

	"github.com/HikoQiu/go-eureka-client/eureka"
)

var resp = make(chan string)
var out = make(chan string)

func main() {
	//code:="#include<stdio.h>\n int main()\n{printf(\"Hello Go Lang\");return 0;}"
	//start := time.Now()
	//var wg sync.WaitGroup
	//for i:=0;i<1000 ;i++ {
	//	wg.Add(1)
	//	go func(wg*sync.WaitGroup,i int) {
	//		go saveToFile(code)
	//		fName:=<-resp
	//		go runCode(wg,fName)
	//		ret := <-out
	//		fmt.Println(ret)
	//	}(&wg,i)
	//}
	//wg.Wait()
	//elapsed := time.Since(start)
	//fmt.Println(elapsed)
	config := eureka.GetDefaultEurekaClientConfig()
	config.UseDnsForFetchingServiceUrls = false
	config.Region = "region-us-east-1"
	config.AvailabilityZones = map[string]string{
		"region-us-east-1": "zone-us-east-1",
	}
	config.ServiceUrl = map[string]string{
		"zone-us-east-1": "http://localhost:1111/eureka",
	}

	// custom logger
	//eureka.SetLogger(func(level int, format string, a ...interface{}) {
	//    if level == eureka.LevelError {
	//        fmt.Println("[custom logger error] " + format, a)
	//    }else {
	//        fmt.Println("[custom logger] " + format, a)
	//    }
	//})

	// run eureka client async
	eureka.DefaultClient.Config(config).
		Register("codeengine", 8084).
		Run()
	fmt.Println("Started Server of port 8084")
	http.HandleFunc("/api/execute", controllers.CodeHandler)
	http.HandleFunc("/api/checkStatus", controllers.ContainerHandler)
	http.ListenAndServe(":8084", nil)
	select {}
}
