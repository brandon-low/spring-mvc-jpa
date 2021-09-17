package hello.sample.test.basic.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/greeting")
public class GreetingController {
	private static final Logger LOG = LoggerFactory.getLogger(GreetingController.class);

	private final GreetingService service;

	public GreetingController(GreetingService service) {
		this.service = service;
	}

	@RequestMapping("/greeting")
	public @ResponseBody String greeting() {
		return service.greet();
	}
	
	private Sample getSample() {
		return new Sample(1, "testme", "good@gmail.com");
	}
	
	private List<Sample> getSamples() {
		List<Sample> list = new ArrayList<Sample>();
		list.add(new Sample(2, "Go You", "You@gmail.com"));
		list.add(new Sample(3, "Go Me", "me@gmail.com"));
		list.add(new Sample(4, "Go Them", "them@gmail.com"));
		return list;
	}
	
	/** get */
	
	@GetMapping(value = "/getall_list")
	public List<Sample> getAllSamplesInList()	{
		System.out.println("********** Do GET SAMPLES In LIST WON'T WORK BLOCKED BY THYME LEAF PASSED STUUF to Model view**********" + getSamples());
		return getSamples();
	}
	
	@GetMapping(value = "/getall")
	public ResponseEntity<List<Sample>> getAllSamples()	{
		System.out.println("********** Do GET SAMPLES WORKS WITH MOCKMVC **********" + getSamples());
		return new ResponseEntity<List<Sample>>( getSamples(), HttpStatus.OK);
		
		// if error ResponseEntity("Error message", HttpStatus.MULTI_STATUS);
	}
	 
	 private static ResponseEntity<Map> generateResponse(String message, HttpStatus status, Object responseObj) {
		 	Map<String, Object> map = new HashMap<String, Object>();
		 	map.put("message", message);
		 	map.put("status", status.value());
		 	map.put("data", responseObj);
		 	return new ResponseEntity<Map>(map,status);
	 }
	
	@GetMapping(value = "/sample/{id}") 
	public ResponseEntity<Sample> getSampleById (@PathVariable("id") int id)
	{
		LOG.debug("Do GET");
		return new ResponseEntity<Sample>(getSample(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/sampleJson/{id}") 
	//@ResponseStatus(HttpStatus.OK)
	public /*@ResponseBody */  JSONObject getSampleByIdJSON (@PathVariable("id") int id) throws Exception	{
		LOG.debug("Do GET");
		
		JSONObject json = new JSONObject ();
		json.put("status", HttpStatus.OK);
		json.put("data", getSample());
		return json;

	}
	
	

	@PostMapping(value = "/save")
	public ResponseEntity<Sample> saveSample (@Valid @RequestBody Sample sample) {
		    return new ResponseEntity<Sample>(sample, HttpStatus.CREATED);
	}
	 
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Sample> updateSample (@PathVariable("id") int id, @Valid @RequestBody Sample sample) {
		return new ResponseEntity<Sample>(sample, HttpStatus.OK);
	}
	 
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<HttpStatus> deleteSample (@PathVariable("id") int id) {
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
	}
	
	
	
	/**
	 * 
	 // Add
    @PostMapping(value = "/users")
    public ResponseEntity<Object> Post(@RequestBody UserEntity params) {
        try {
            UserEntity result = userService.Post(params);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    // Get
    @GetMapping(value = "/users")
    public ResponseEntity<Object> Get() {
        try {
            List<UserEntity> result = userService.Get();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Get By ID
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Object> Get(@PathVariable int id) {
        try {
            UserEntity result = userService.Get(id);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Update
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<Object> Update(@PathVariable int id, @RequestBody UserEntity params) {
        try {
            UserEntity result = userService.Update(params, id);
            return ResponseHandler.generateResponse("Updated", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Delete
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> Delete(@PathVariable int id) {
        try {
            String result = userService.Delete(id);
            return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    

	 */
}
