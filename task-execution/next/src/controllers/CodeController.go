package controllers

import (
	"encoding/json"
	"fmt"
	"models"
	"net/http"
	"sync"
	u "utils"
)

var cMap sync.Map
func CodeHandler(w http.ResponseWriter,r *http.Request) {
	var code =  models.Code{}
	var resp = make(chan string,10)
	var out = make(chan string,10)
	var container = models.NewContainer()
	cMap.Store(container.Id,&container)
	json.NewDecoder(r.Body).Decode(&code)
		var wg sync.WaitGroup
		wg.Add(1)
		go func(wg *sync.WaitGroup) {
			defer wg.Done()
			go code.SaveToFile(resp)
			fName:=<-resp
			go code.RunCode(fName,out,r.Context())
			ret := <-out
			opaque,_:=cMap.Load(container.Id)
			containerRef:=opaque.(*models.Container)
			containerRef.Output=ret
			containerRef.State="Completed"
			fmt.Println(ret)
			//select {
			//case ret := <-out:
			//	fmt.Println(ret)
			//case <-time.After(3*time.Second):
			//	fmt.Println("Timeout")
		}(&wg)
	msg := u.Message(true, "Started Execution")
	msg["cid"]=container.Id
	u.Respond(w,msg)
}


func ContainerHandler(w http.ResponseWriter,r *http.Request){
	if r.Method=="POST"{
		return
	}
	if opaque,ok:=cMap.Load(r.URL.Query()["cid"][0]);ok {
		msg := u.Message(true, "Finished Execution")
		container:=opaque.(*models.Container)
		msg["cid"] =container.Id
		msg["status"]=container.State
		msg["output"]=container.Output
		if msg["status"]=="Completed"{
			cMap.Delete(container.Id)
		}
		u.Respond(w,msg)
	}
}
