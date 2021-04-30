package cz.educanet;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

import java.util.ArrayList;

import static cz.educanet.Main.Maze;
import static cz.educanet.Main.W;
import static cz.educanet.Main.H;

public class Window {
    public static void Okno() throws Exception {
        int a = (int) Math.sqrt(Maze.length());


        Maze = Maze.replace("\n", "");
        GLFW.glfwInit();
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        long window = GLFW.glfwCreateWindow(W, H, "Maze", 0, 0);
        if (window == 0) {
            GLFW.glfwTerminate();
            throw new Exception("Can't open window");
        }
        GLFW.glfwMakeContextCurrent(window);


        GL.createCapabilities();
        GL33.glViewport(0, 0, W, H);

        GLFW.glfwSetFramebufferSizeCallback(window, (win, w, h) -> GL33.glViewport(0, 0, w, h));
        Shaders.initShaders();

        ArrayList<Integer> pozice = new ArrayList<Integer>();

        float x = -1.0f;
        float y = 1.0f;
        int at = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                System.out.println(i + " , " + j);
                System.out.println(at);
                if (Maze.charAt(at) == '1') {

                    float[] pos = {
                            x + 2.0f / (float) a, y, 0.0f,
                            x + 2.0f / (float) a, y - 2.0f / (float) a, 0.0f,
                            x, y - 2.0f / (float) a, 0.0f,
                            x, y, 0.0f,
                    };

                    Square sqr = new Square();
                    sqr.setedges(pos);

                    pozice.add(sqr.getSquareVaoId());

                    x += 2.0f / (float) a;
                } else {
                    x += 2.0f / (float) a;
                }
                at++;
            }
            x = -1.0f;
            y -= 2.0f / (float) a;
        }


        while (!GLFW.glfwWindowShouldClose(window)) {
            GL33.glUseProgram(Shaders.shaderProgramId);

            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS)
                GLFW.glfwSetWindowShouldClose(window, true);

            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            for (Integer integer : pozice) {
                GL33.glBindVertexArray(integer);
                GL33.glDrawElements(GL33.GL_TRIANGLES, 6, GL33.GL_UNSIGNED_INT, 0);
            }


            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

        GLFW.glfwTerminate();
    }

}
