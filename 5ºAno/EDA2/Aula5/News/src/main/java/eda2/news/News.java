package eda2.news;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

enum Cor {WHITE, GREY, BLACK};

public class News
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{
		//declarar um buffer para puder ler inputs
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		
		//ler o numero de empregados
		int nEmployees = Integer.parseInt(buffer.readLine());

		ArrayList<Node>[] arrList = new ArrayList[nEmployees];

		for (int i = 0; i < nEmployees; i++)
		{
			String[] sEmployees = buffer.readLine().split(" ");

			arrList[i] = new ArrayList<Node>();

			for (int j = 1; j <= Integer.parseInt(sEmployees[0]); j++)
			{
				Node node = new Node();
				node.valor = Integer.parseInt(sEmployees[j]);
				node.cor = Cor.WHITE;
				node.dist = Integer.MAX_VALUE;
				arrList[i].add(node);
			}
		}

		int tentativas = Integer.parseInt(buffer.readLine());

		for (int l = 0; l < tentativas; l++)
		{
			System.out.println();
			//System.out.println("entra");
			int raiz = Integer.parseInt(buffer.readLine());

			System.out.println();
			for (int i = 0; i < nEmployees; i++)
			{
				for (int j = 0; j < arrList[i].size(); j++)
					//System.out.print(arrList[i].get(j).cor + " ");

				System.out.println();
			}

			BFS(arrList, raiz);
                        
                        
                        System.out.println(Arrays.toString(arrList));

			
		}

		

	}

	public static void BFS(ArrayList<Node>[] arrList, int raiz)
	{
		//int pos = arrList[raiz].get(0).indexOf(raiz);
		arrList[raiz].get(0).cor = Cor.GREY;
		arrList[raiz].get(0).dist = 0;
		Queue fila = new Queue();
		fila.add(arrList[raiz].get(0));
		while(!fila.isEmpty())
		{
			Node aux = (Node) fila.remove();
			for (Node element : arrList[aux.valor] )
			{
				if(element.cor == Cor.WHITE)
				{
					element.cor = Cor.GREY;
					element.dist = aux.dist + 1;
				}
				fila.add(element);
			}
			aux.cor = Cor.BLACK;
		}
	}
}