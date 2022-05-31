package labirinto;

public class Percorrer {
  
  //Atributos do labirinto
  Object[][] vetLabirinto = null;
  int nLinhas, nColunas;
  Coordenada Atual = null;

  Pilha<Coordenada> caminho = null; //Pilha de coordenadas do caminho do labirinto
  Pilha<Coordenada> caminhoInverso = null; //Pilha inversa de coordenadas do caminho do labirinto 

  Pilha<Fila<Coordenada>> possibilidades = null; //Pilha de Fila de Coordenada com as possibilidades de caminho do labirinto

  Fila<Coordenada> filaDeAdjacentes = null; // Fila com as coordenadas

  public Percorrer(Object[][] vetLabirinto, int nLinhas, int nColunas) throws Exception {
    try {
        this.nLinhas = nLinhas;
        this.nColunas = nColunas;
        this.vetLabirinto = vetLabirinto;

        int tamanho = (nLinhas - 1) * nColunas;

        this.caminho = new Pilha<Coordenada>(tamanho);
        this.caminhoInverso = new Pilha<Coordenada>(tamanho);

        this.possibilidades = new Pilha<Fila<Coordenada>>(tamanho);
      }

    catch(Exception Error) {
      throw new Exception(Error);
    }

    try{
      validaLabirinto();
    }

    catch(Exception Error) {
      throw new Exception(Error);
    }
  }
  //Pega caminho para a pilha de coordenada
  public Pilha<Coordenada> getCaminho() throws Exception {
    return this.caminho;
  }

  //Função para pegar algum elemento do caminho do labirinto
  private String getElemento() throws Exception
  {
    int linha = 1;
    String getElemento = "";

    //Enquanto linha for menor que o número de linhas do arquivo
    while(linha < this.nLinhas)
      {
        for(int coluna = 0; coluna < this.nColunas; coluna++)
	    	  {
	    		  getElemento = getElemento + this.vetLabirinto[linha][coluna];
	    	  }
	    	  getElemento = getElemento+"\n";
	    	  linha++;
      }
    
    //Enquanto a pilha de caminho for diferente de vazio, guardar no caminho inverso um elemento recuperado dela, e depois remover dela para não duplicar
    while(!this.caminho.isVazia())
      {
        this.caminhoInverso.guardeUmItem(this.caminho.recupereUmItem());
        this.caminho.removaUmItem();
      }

    //Enquanto a pilha de caminho inverso for diferente de vazio, recupera o elemento coordenada sendo linha -1 e a coluna mantém, e remove dela para não duplicar
    while(!this.caminhoInverso.isVazia())
      {
        getElemento = getElemento + "(" + (((Coordenada)this.caminhoInverso.recupereUmItem()).getX() - 1) +
          "," +  ((Coordenada)this.caminhoInverso.recupereUmItem()).getY() + ") ";
        this.caminhoInverso.removaUmItem();
      }
    
    return getElemento;
  }

  //Função para resolver o labirinto, andar para frente ou para trás
  public String resolucaoDeLabirinto() throws Exception {
	   /*if(this.vetLabirinto == null)
	   {
		   throw new Exception ("\nOcorreu um erro ao carregar o labirinto. Por favor, forneça o caminho novamente.\n");
	   }*/

     while(!acharCaminho(this.Atual)) {
      //PROGRESSIVO - para frente
      //a fila de adj não pode ser vazia senão não tem pra onde ir, então recupera e remove dela
      //coloca o * no vetor da coord, e guarda a atual na pilha caminho
      // nao esquecer de guardar adj nas possibi caso precise regredir
      if(!this.filaDeAdjacentes.isVazia()) {
        this.Atual = this.filaDeAdjacentes.recupereUmItem();
        this.filaDeAdjacentes.removaUmItem();
        this.vetLabirinto[this.Atual.getX()][this.Atual.getY()] = "*";
        this.caminho.guardeUmItem(this.Atual);
        this.possibilidades.guardeUmItem(this.filaDeAdjacentes);
      }

      //REGRESSIVO - para trás
      //remove da pilha caminho, pega do vetor a coordenada atual e coloca espaço em branco
      //setar na atual, a recuperada da pilha caminho, e setar na fila de adj a recuperada da possibilidade
      //não esquecer de remover para não gerar duplicidade
      else {
        this.caminho.removaUmItem();
        this.vetLabirinto[this.Atual.getX()][this.Atual.getY()] = " ";
        if(this.caminho.isVazia())
          throw new Exception("Esse labirinto não possui saída.");

        this.Atual = this.caminho.recupereUmItem();
        this.filaDeAdjacentes = (this.possibilidades.recupereUmItem());
        this.possibilidades.removaUmItem();
      }
    }
    return getElemento();
  }

  //Coordenada atual e verifica se há adjacente dela (a frente, acima, atras e abaixo)
  private boolean acharCaminho(Coordenada atual) throws Exception {
    int x = atual.getX();
    int y = atual.getY();
    Coordenada auxAdjacente = null;
    this.filaDeAdjacentes = new Fila<Coordenada>();
    
    //direita - frente
    try {
      if(x <= this.nLinhas-1 && y+1 <= this.nColunas-1)
        if(this.vetLabirinto[x][y+1].equals('S')) {
          this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x,y+1));
          return true;
        }
        else if(this.vetLabirinto[x][y+1].equals(' ')) {
          this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x,y+1));
        }
    }

    catch(Exception Error) {
        throw new Exception (Error);
      }

    //acima
    try {
    if(x-1 <= this.nLinhas-1 && y <= this.nColunas && x-1 >= 1)
      if(this.vetLabirinto[x-1][y].equals('S')) {
        this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x-1,y));
        return true;
      }
      else if(this.vetLabirinto[x-1][y].equals(' ')) {
        this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x-1,y));
      }
    }

    catch(Exception Error) {
      throw new Exception (Error);
    }
    //esquerda - atrás
    try {
      if(x <= this.nLinhas-1 && y-1 <= this.nColunas-1 && y-1 >= 0)
        if(this.vetLabirinto[x][y-1].equals('S')) {
          this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x,y-1));
          return true;
        }
      else if(this.vetLabirinto[x][y-1].equals(' ')) {
        this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x,y-1));
      }
    }

    catch(Exception Error) {
        throw new Exception (Error);
      }
    
    //abaixo
    try {
      if(x+1 <= this.nLinhas-1 && y <= this.nColunas)
        if(this.vetLabirinto[x+1][y].equals('S')) {
          this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x+1,y));
          return true;
        }
        else if(this.vetLabirinto[x+1][y].equals(' ')) {
          this.filaDeAdjacentes.guardeUmItem(auxAdjacente = new Coordenada(x+1,y));
        }
      }

      catch(Exception Error) {
        throw new Exception (Error);
      }

    return false;
  }

    //Função para validar os possíveis erros do labirinto, conforme labirintos errados do André
    private void validaLabirinto() throws Exception
    {
    String  aux = null;
    int nParede = 0;
    int nBranco = 0;
    int i = 0;
    int nEntradas = 0;
    int nSaidas = 0;

    if(this.vetLabirinto[0][0] == null)   
    {
      throw new Exception ("\n Deve haver o número de linha do labirinto indicado na primeira linha do arquivo. \n");
    }

    while(i < this.nColunas)
      {
        //System.out.println(this.nColunas + " Colunas"); 
        
        if(this.vetLabirinto [0][i] == null)
        {
          break;
        }

        if(aux == null)
        {
          aux = this.vetLabirinto[0][i].toString();
        }

        else
        {
          aux = aux + (this.vetLabirinto[0][i].toString());
        }

        try{
          Integer.parseInt(aux);
        }

        catch(Exception Error)
          {
            throw new Exception ("Número de linhas não encontrado, por favor verifique e tente novamente.");
          }
        i++;
      }

    for(int linha = 1; linha < this.nLinhas; linha++)
      {
        for(int coluna = 0; coluna<this.nColunas; coluna++) {
          try {
            if(this.vetLabirinto[linha][coluna].equals('S')) {
              if((linha > 1 && linha < this.nLinhas-1) && (coluna > 0 && coluna < this.nColunas-1)) {
                throw new Exception ("\nApenas saídas pelas bordas são válidas!\n");
              }
              nSaidas++;
            }
            else if(this.vetLabirinto[linha][coluna].equals('E')) {
              this.Atual = new Coordenada(linha,coluna);
              if((linha > 1 && linha < this.nLinhas-1) && (coluna > 0 && coluna < this.nColunas-1)) {
                throw new Exception ("\nApenas entradas pelas bordas são válidas!\n");
              }
              nEntradas++;
            }
            else if(this.vetLabirinto[linha][coluna].equals('#')) {
              nParede++;
            }

            else if(this.vetLabirinto[linha][coluna].equals(' ')) {
              if((linha == 1 || linha == this.nLinhas-1 || coluna == 0 || coluna == this.nColunas-1)) {
                throw new Exception ("\nNao pode ter espaços na borda\n");
              }
              nBranco++;
            }

            else{
              throw new Exception ("\n O caracter "+this.vetLabirinto[linha][coluna].toString()+" é inválido!! \n");
            }
          }
            catch(Exception Error) {
              throw new Exception(Error);
          }            
        }
      }

    if(aux != null) {
      int auxiliar = Integer.parseInt(aux);

      if(auxiliar < (this.nLinhas-1)) {
        throw new Exception ("\nNúmero de linhas inválido, por favor corrija e tente novamente. \n");
      }

      else if(auxiliar > (this.nLinhas-1)) {
        throw new Exception ("\nNúmero de linhas inválido, por favor corrija e tente novamente. \n");
      }

      if(nSaidas > 1) {
        throw new Exception("\nLabirinto inválido. É permitido no máximo uma saída ('S') \n");
      }

      if(nEntradas >1) {
          throw new Exception("\nLabirinto inválido. É permitido no máximo uma entrada ('E') \n");
        }
      
        if(nSaidas < 1) {
          throw new Exception("\nO labirinto não possui caracter de saída('S'), por favor corrija e tente novamente. \n");
        }
      
        if(nEntradas < 1) {
          throw new Exception("\nO labirinto não possui caracter de entrada ('E'), por favor corrija e tente novamente. \n");
        }
    }
    else {
      throw new Exception("\nA primeira linha do arquivo deve ser o número de linhas do labirinto.");
    }
  }
}