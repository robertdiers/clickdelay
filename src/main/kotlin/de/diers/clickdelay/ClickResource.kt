package de.diers.clickdelay

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URL;
import java.util.Properties;
import java.io.FileInputStream;

import java.util.Timer
import java.util.Date
import java.text.SimpleDateFormat
import kotlin.concurrent.schedule

@RestController
class ClickResource {

    @GetMapping("/click/{id}/{waittime}")
    fun click(@PathVariable id:String, @PathVariable waittime:Long): String {

        val sdf = SimpleDateFormat("dd/MM/yyyy HH24:mm:ss")

        //read switch url    
        var envVar = getProp<String>("tasmota."+id+".switch")

        //switch
        var status = URL(envVar).readText()        
        println(sdf.format(Date())+" clicked "+id+": "+status)

        //create timer to stop it - we can do this for every execution...
        Timer("StopTimer", false).schedule(1000*waittime) { 
            if (status(id).contains("ON")) {
                println(sdf.format(Date())+" timer "+id+": "+URL(envVar).readText())
            } else {
                println(sdf.format(Date())+" timer "+id+": is off")
            }
        }

        return status(id)
    }

    @GetMapping("/status/{id}")
    fun status(@PathVariable id:String): String {
        
        //read status url    
        var envVar = getProp<String>("tasmota."+id+".status")

        //read status      
        var status = URL(envVar).readText()

        return status        
    }

    @GetMapping("/color/{id}")
    fun color(@PathVariable id:String): String {
        
        if (status(id).contains("ON")) {
            return "#DAF7A6"
        } else {
            return "#FF5733"
        }               
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getProp(key: String): T {
        val props  = javaClass.classLoader.getResourceAsStream("application.properties").use {
            Properties().apply { load(it) }
        }
        return (props.getProperty(key) as T) ?: throw RuntimeException("could not find property $key")
    }

}