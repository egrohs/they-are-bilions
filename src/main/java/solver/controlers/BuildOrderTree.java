package solver.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import solver.services.State;
import solver.services.TABillions;

@RestController
@RequestMapping(path = "/tree")
public class BuildOrderTree {
	@Autowired
	private TABillions trb;
	@GetMapping("/all")
	@ApiOperation(value = "Retorna a arvore da build atualmete explorada.")
	@ResponseBody
	public State getTree() {
		return trb.getRoot();
	}
}