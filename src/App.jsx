import Formulario from "./Formulario"
import Tabela from "./Tabela"
import './App.css'
import { useEffect, useState } from "react"

function App() {

	//obj produto
	const produto = {
		codigo: 0,
		name: '',
		marca: ''
	}

	const [btnCadastar, setBtnCadastrar] = useState(true)
	const [produtos, setProdutos] = useState([])
	const [objProduto, setObjProduto] = useState(produto)

	useEffect(() => {
		fetch("http://localhost:2345/listar")
			.then(retorno => retorno.json())
			.then(retorno_convertido => setProdutos(retorno_convertido))
	}, [])

	//obtendo dados do formulario
	const aoDigitar = (e) => {
		setObjProduto({ ...objProduto, [e.target.name]: e.target.value });
	}

		
	 // Cadastrar produto
  const cadastrar = () => {
    fetch('http://localhost:2345/cadastrar',{
      method:'POST',
      body:JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        setProdutos([...produtos, retorno_convertido]);
        alert('Produto cadastrado com sucesso!');
        limparFormulario();
      }
      
    })
  }
	
	const limparFormulario = () => {
		setObjProduto(produto);
	}

	return (
		<div>
			<Formulario botao={btnCadastar} eventoTeclado={aoDigitar} cadastrar={cadastrar}/>
			<Tabela vetor={produtos} />
		</div>
	)
}

export default App
