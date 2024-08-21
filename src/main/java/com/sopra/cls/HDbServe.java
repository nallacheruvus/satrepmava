package com.sopra.cls;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dbops")
public class HDbServe {

	DbHelper dbu=new DbHelper();
	@GetMapping("/sel")
	public List<Persons> retLi() throws SQLException{
		return dbu.retList();
	}
	
	@PostMapping("/ins/{pid}/{pname}/{pemail}")
	public String insRec(@PathVariable("pid") int pid,@PathVariable("pname") String pname,@PathVariable("pemail") String pemail) {
		Persons p=new Persons();
		p.setPid(pid);
		p.setPname(pname);
		p.setPemail(pemail);
		return dbu.insRec(p);
	}
	
	@PutMapping("/ups/{pid}/{pname}/{pemail}")
	public String upsRec(@PathVariable("pid") int pid,@PathVariable("pname") String pname,@PathVariable("pemail") String pemail) {
		Persons p=new Persons();
		p.setPid(pid);
		p.setPname(pname);
		p.setPemail(pemail);
		return dbu.upsRec(p);
	}
	
	@DeleteMapping("/del/{pid}")
	public String delRec(@PathVariable("pid") int pid) {
		return dbu.delRec(pid);
	}
	
	
}
