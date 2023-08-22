package br.com.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.entities.ProdutoModelo;
import br.com.entities.RespostaModelo;
import br.com.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {

	@Autowired
	private ProdutoRepositorio pr;
	
	@Autowired
	private RespostaModelo rm;
	
	public Iterable<ProdutoModelo>listar(){
		return pr.findAll();
	}
	
	public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao){
	
		if(pm.getName().equals("")) {
			rm.setMensagem("Insira o Nome do Produto");
			return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
		}else if(pm.getMarca().equals("")) {
			rm.setMensagem("Insira o Nome da Marca");
			return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
		}else if(acao.equals("cadastrar")){
			return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.CREATED);
		}else {
			return new ResponseEntity<ProdutoModelo>(pr.save(pm), HttpStatus.OK);

		}
	}
	
	public ResponseEntity<RespostaModelo> remover(Long codigo){
		pr.deleteById(codigo);
		rm.setMensagem("Produto Deletado!");
		return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
	}
}
