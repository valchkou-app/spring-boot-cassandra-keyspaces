package com.valchkou.demo.common.swagger

import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@Slf4j
@RestController
class SwaggerRedirectController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView redirect2() {
        return new ModelAndView("redirect:/swagger-ui.html")
    }

    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public ModelAndView redirect3() {
        return new ModelAndView("redirect:/swagger-ui.html")
    }
}
