package io.pivotal.labs.eoslookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CompatibilityController {

    @Autowired
    private CameraRepository cameraRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView lensInformation() {
        List<Camera> cameras = cameraRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("lens-information");
        modelAndView.addObject("cameras", cameras);

        return modelAndView;
    }

}
