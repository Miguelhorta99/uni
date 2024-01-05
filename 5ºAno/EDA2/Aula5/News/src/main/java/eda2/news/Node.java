
package eda2.news;

public class Node {

    int valor;
    Cor cor;
    int dist;
    
    public void setValores(int valor)
	{
		this.valor = valor;
		this.cor = Cor.WHITE;
		this.dist = 0;
	}
   
}
