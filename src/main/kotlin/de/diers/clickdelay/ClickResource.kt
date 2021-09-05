package de.diers.clickdelay

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URL;

@RestController
class ClickResource {

    @GetMapping("/click/{id}/{waittime}")
    fun click(@PathVariable id:String, @PathVariable waittime:Int): String {

        //on        
        URL("https://google.com").readText()

        var envVar: String = System.getenv("tasmota.one") ?: "n/a"

        return "clicked: "+id+" - waiting for "+waittime+" sec "+envVar
        
    }

}