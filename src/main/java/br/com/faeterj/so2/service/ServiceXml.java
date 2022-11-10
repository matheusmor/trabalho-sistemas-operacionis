package br.com.faeterj.so2.service;

import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.faeterj.so2.model.NfeValor;
import br.com.faeterj.so2.repository.NfeRepository;

@Service
public class ServiceXml {
	
	@Autowired
	NfeRepository repository;
	
	public List<NfeValor> getAll() {
		
			
				List<NfeValor> nfeValor = repository.findAll();
			
		return nfeValor;
	}
	
	public String saveXml(String content) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder; 
        builder = factory.newDocumentBuilder();
        Document doc = builder.parse( new InputSource( new StringReader( content ) ) ); 
        doc.getDocumentElement().normalize();  
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
        NfeValor objeto = new NfeValor();
        
        Element eElement = (Element) doc.getElementsByTagName("dest").item(0);
        objeto.setNomeCliente(eElement.getElementsByTagName("xNome").item(0).getTextContent());
        

    
		System.out.println(doc.getElementsByTagName("emit").getLength());
		System.out.println( doc.getElementsByTagName("emit"));
		eElement = (Element) doc.getElementsByTagName("emit").item(0); 
		
		System.out.println(eElement.getElementsByTagName("xNome").item(0).getTextContent());
		
		objeto.setNomeEmissor(eElement.getElementsByTagName("xNome").item(0).getTextContent());
		
		objeto.setCnpj(eElement.getElementsByTagName("CNPJ").item(0).getTextContent());
		
		System.out.println(doc.getElementsByTagName("prod").getLength());
		NodeList nodeList = doc.getElementsByTagName("prod");  
		String custoReal = "";
		for (int itr = 0; itr < nodeList.getLength(); itr++) {
			System.out.println(itr);
			double total = 0;
			System.out.println(nodeList.item(itr));
			
			System.out.println(nodeList.item(itr));
			
			System.out.println(Double.parseDouble(doc.getElementsByTagName("vProd").item(itr).getTextContent()));
			total = Double.parseDouble(doc.getElementsByTagName("vProd").item(itr).getTextContent());
			System.out.println(doc.getElementsByTagName("vFCP").item(itr).getTextContent() );
			total+= Double.parseDouble(doc.getElementsByTagName("vFCP").item(itr).getTextContent());
			System.out.println(doc.getElementsByTagName("vICMSST").item(itr).getTextContent() );
			total+= Double.parseDouble(doc.getElementsByTagName("vICMSST").item(itr).getTextContent());
			System.out.println(doc.getElementsByTagName("vIPI").item(itr).getTextContent() );
			total+= Double.parseDouble(doc.getElementsByTagName("vIPI").item(itr).getTextContent());
			System.out.println(doc.getElementsByTagName("qTrib").item(itr).getTextContent() );
			total+= Double.parseDouble(doc.getElementsByTagName("qTrib").item(itr).getTextContent());
			System.out.println(doc.getElementsByTagName("xProd").item(itr).getTextContent() );
			System.out.println(doc.getElementsByTagName("qCom").item(itr).getTextContent() );
			System.out.println(doc.getElementsByTagName("qTrib").item(itr).getTextContent() );
			DecimalFormat df = new DecimalFormat("0.00");
			String string = " n"+itr+": "+" - custo final: "+ df.format(total)+" - nome do produto:"+doc.getElementsByTagName("xProd").item(itr).getTextContent()+" - quantidade de caixas: "+doc.getElementsByTagName("qCom").item(itr).getTextContent()+"- quantidade convertida: "+doc.getElementsByTagName("qTrib").item(itr).getTextContent()+ " | ";
			custoReal= custoReal.concat(string)  ;
			

		}   
		System.out.println(custoReal);
		objeto.setProduto(custoReal);
		//eElement = (Element) doc.getElementsByTagName("det").item(0); 
		
		
		repository.save(objeto);
		
		
		
		return "dados inseridos com sucesso";
		
		
		
	}
	
	
	private NfeValor findNfe(Integer id) throws Exception  {
		Optional<NfeValor> find = repository.findById(id);
		if (!find.isPresent()) {
			throw new Exception("id não encontrado");
		}
		return find.get();

	}
	public NfeValor deleteNfe(Integer id) throws Exception  {

		NfeValor nfeValor = findNfe(id);
		repository.delete(nfeValor);
		return nfeValor;
	}

	public NfeValor updateNfeVlor(Integer id, NfeValor nfeNovo) throws Exception  {
		NfeValor found = findNfe(id);
		if (nfeNovo.getId() != null)
			found.setId(nfeNovo.getId());
		if (nfeNovo.getCnpj() != null)
			found.setCnpj(nfeNovo.getCnpj());
		if (nfeNovo.getNomeCliente() != null)
			found.setNomeCliente(nfeNovo.getNomeCliente());
		if (nfeNovo.getNomeEmissor() != null)
			found.setNomeEmissor(nfeNovo.getNomeEmissor());
		if (nfeNovo.getProduto() != null)
			found.setProduto(nfeNovo.getProduto());
		
		found = repository.save(found);
		return found;

	}
}
