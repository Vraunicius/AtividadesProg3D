package br.pucpr.cg;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import jdk.nashorn.internal.runtime.Context;
import org.joml.Vector3f;

import br.pucpr.mage.Mesh;
import br.pucpr.mage.MeshBuilder;

public class MeshFactory {
    public static Mesh createPentagon() {
        return new MeshBuilder()
                .addVector3fAttribute("aPosition",
                        -0.5f,  0.25f, 0.0f,  //0
                        0.5f,  0.25f, 0.0f,  //1
                        -0.30f, -0.25f, 0.0f,  //2
                        0.30f, -0.25f, 0.0f,  //3
                        0.0f, 0.5f,0.0f)  //4
                .addVector3fAttribute("aColor",
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f)
                .setIndexBuffer(
                        0,  2,  3,
                        0,  3,  1,
                        4, 0, 1)
                .loadShader("/br/pucpr/resource/basic")
                .create();
    }
    public static Mesh createCube2() {

        return new MeshBuilder()
                .addVector3fAttribute("aPosition",

                        -0.5f,  0.5f, 0.0f,  //0
                        0.5f,  0.5f, 0.0f,  //1
                        -0.5f, -0.5f, 0.0f,  //2
                        0.5f, -0.5f, 0.0f,  //3

                        -0.5f,  0.5f, -1f,  //4
                        0.5f,  0.5f, -1f,  //5
                        -0.5f, -0.5f, -1f,  //6
                        0.5f, -0.5f, -1f,  //7

                        -0.5f,  0.5f, 0.0f,  //8
                        -0.5f,  0.5f, -1f,  //9
                        -0.5f, -0.5f, 0.0f,  //10
                        -0.5f, -0.5f, -1f, //11

                        0.5f,  0.5f, 0.0f,  //12
                        0.5f,  0.5f, -1f,  //13
                        0.5f, -0.5f, 0.0f,  //14
                        0.5f, -0.5f, -1f,  //15

                        0.5f,  0.5f, 0.0f,  //16
                        0.5f,  0.5f, -1f,  //17
                        -0.5f, 0.5f, 0.0f,  //18
                        -0.5f, 0.5f, -1f,  //19

                        0.5f,  -0.5f, 0.0f,  //20
                        0.5f,  -0.5f, -1f,  //21
                        -0.5f, -0.5f, 0.0f,  //22
                        -0.5f, -0.5f, -1f)  //23



                .addVector3fAttribute("aColor",
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f,

                        1.0f, 0.0f, 0.0f,
                        1.0f, 0.0f, 0.0f,
                        1.0f, 0.0f, 0.0f,
                        1.0f, 0.0f, 0.0f,

                        0.0f, 1.0f, 0.0f,
                        0.0f, 1.0f, 0.0f,
                        0.0f, 1.0f, 0.0f,
                        0.0f, 1.0f, 0.0f,

                        0.0f, 1.0f, 1.0f,
                        0.0f, 1.0f, 1.0f,
                        0.0f, 1.0f, 1.0f,
                        0.0f, 1.0f, 1.0f,

                        1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f,

                        1.0f, 0.0f, 1.0f,
                        1.0f, 0.0f, 1.0f,
                        1.0f, 0.0f, 1.0f,
                        1.0f, 0.0f, 1.0f)
                .setIndexBuffer(
                        0,  2,  3,
                        0,  3,  1,

                        5,  7,  6,
                        5,  6,  4,

                        9, 11, 10,
                        9, 10,  8,

                        12,14, 15,
                        12,15, 13,

                        19,18, 16,
                        17,19, 16,

                        22, 23, 21,
                        20, 22, 21)
                .loadShader("/br/pucpr/resource/basic")
                .create();
    }
    public static Mesh loadTerrain2(File file, float scale) throws IOException {
        BufferedImage Terrain2 = ImageIO.read(file);
        int largura, profundidade;
        largura = Terrain2.getWidth();
        profundidade = Terrain2.getHeight();

        List<Vector3f> VerticesLista = new ArrayList<>();
        for(int z = 0;z<profundidade;z++)
        {
            for(int x = 0;x<largura;x++)
            {
                Color cor = new Color(Terrain2.getRGB(x,z));
                VerticesLista.add(new Vector3f(x - largura/2,cor.getRed()*scale,z - profundidade/2));
            }
        }
        List<Integer> Index = new ArrayList<>();
        for(int z = 0;z<profundidade - 1;z ++)
        {
            for(int x = 0;x<largura - 1;x ++)
            {
                int Vertice0 = x + z * largura;
                int Vertice1 = (x + 1) + z * largura;
                int Vertice2 = x + (z + 1) * largura;
                int Vertice3 = (x + 1) + (z + 1) * largura;

                Index.add(Vertice0);
                Index.add(Vertice2);
                Index.add(Vertice3);

                Index.add(Vertice0);
                Index.add(Vertice3);
                Index.add(Vertice1);
            }
        }

        List<Vector3f> Colors = new ArrayList<>();
            for(int z = 0;z<profundidade;z++)
            {
                for(int x = 0;x<largura;x++)
                {
                    Colors.add(new Vector3f(1,1,0));
                }
            }
        return new MeshBuilder().addVector3fAttribute("aPosition",VerticesLista)
                .addVector3fAttribute("aColor",Colors).setIndexBuffer(Index)
                .loadShader("/br/pucpr/resource/basic").create();
    }

}
