package com.kocak.kafka_prod_cons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaKocakController {
	
	private KafkaTemplate<String, KocakModel> kafkaTemplate;

	@Autowired
	public KafkaKocakController(KafkaTemplate<String, KocakModel> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@PostMapping
	public void post(@RequestBody KocakModel kocakModel) {
		kafkaTemplate.send("myTopic", kocakModel);
	}
	
	@KafkaListener(topics = "myTopic")
	public void getFromKafka(KocakModel model) {
		System.out.println(model.getField1() + " " + model.getField2());
	}
	
}
