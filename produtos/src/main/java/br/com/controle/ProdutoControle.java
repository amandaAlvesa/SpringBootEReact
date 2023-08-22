package br.com.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.entities.ProdutoModelo;
import br.com.entities.RespostaModelo;
import br.com.servico.ProdutoServico;

@RestController
@CrossOrigin(origins = "*")
public class ProdutoControle {

	@Autowired
	private ProdutoServico ps;
	
	@DeleteMapping("/deletar/{codigo}")
	public ResponseEntity<RespostaModelo> deletar (@PathVariable long codigo){
		return ps.remover(codigo);
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody ProdutoModelo pm){
		return ps.cadastrarAlterar(pm, "alterar");
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadatrar(@RequestBody ProdutoModelo pm){
		return ps.cadastrarAlterar(pm, "cadastrar");
	}
	
	@GetMapping("/listar")
	public Iterable<ProdutoModelo>listar(){
		return ps.listar();
	}
	
	@GetMapping("/")
	public String rota(){
		return "Api de Produtos Rodando";
	}
}
