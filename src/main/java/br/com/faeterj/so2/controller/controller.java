package br.com.faeterj.so2.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import br.com.faeterj.so2.model.NfeValor;
import br.com.faeterj.so2.service.ServiceXml;



@RestController
public class controller {
	
	@Autowired
	private ServiceXml service;
	
	@GetMapping("/get")
	public ResponseEntity<List<NfeValor>> get(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> postFile(@RequestParam("file") MultipartFile file ) throws IOException, ParserConfigurationException, SAXException{
		String content = new String(file.getBytes());
		
		
		return ResponseEntity.ok(service.saveXml(content));
	}
	
	@DeleteMapping("/nfe/{id}")
	public NfeValor deleteNfe(@PathVariable Integer id) throws Exception {
		return service.deleteNfe(id);
	}
	
	@PutMapping("/Nfe/{id}")
	public NfeValor putNfe(@PathVariable Integer id, @RequestBody NfeValor nfevValor) throws Exception  {
		return service.updateNfeVlor(id,nfevValor);
	}

}
