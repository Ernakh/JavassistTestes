/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhojavassist;

import java.io.IOException;
import javassist.*;
import javassist.bytecode.ClassFile;

/**
 *
 * @author Fabrício
 */
public class TrabalhoJavassist 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException 
    {
        //criar instancia da classe // não utilizado
        //ClassFile cf = new ClassFile(false, "teste", null);

        //cria objeto para receber as propriedades da classe
        ClassPool pool = ClassPool.getDefault();
        //getDefault prepara o o pool para receber uma classe
        //usa o pool para capturar a calsse passada por parametro
        CtClass cc = pool.get("trabalhojavassist.teste");
        //CtClass é um clone da classe
        //Adiciona ao clone,uma outra classe como SuperClasse/Classe Pai
        //apartir daqui, a classe teste(cc) possui os atributos e metodos da classe teste2
        cc.setSuperclass(pool.get("trabalhojavassist.teste2"));
        
        //Adiciona um metodo dinamicamente para a classe(cc)
        //cria o metodo
        CtMethod method = CtNewMethod.make("public void DigaOi() { System.out.println(\"Oi!\"); }", cc);
        //adiciona o metodo na classe
        cc.addMethod(method);
        
        CtField field = CtField.make("public int teste5;", cc);
        cc.addField(field);
        
        //Escreve no ByteCOde
        cc.writeFile();
        
        //pega todos os métodos da classe, inclusive os herdados
        //Para pegar sometne os metodos declarados na classe, usar getDeclaredMethods
        CtMethod[] x = cc.getMethods();

        System.out.println("\nMétodos:\n");
        
        //percorre e mostra os mpetodos
        for (CtMethod x1 : x)
        {
            System.out.println(x1.getName());
        }
        
        //busca os campos
        //não traz os campos privates
        CtField[] y = cc.getFields();
        System.out.println("\nCampos:\n");
        //percorre e mostra os campos
        for (CtField y1 : y)
        {
            System.out.println(y1.getName());
        }
    }
}