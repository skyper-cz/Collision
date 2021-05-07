package cz.educanet;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

import java.util.ArrayList;
import java.util.Scanner;

import static cz.educanet.Main.*;

public class Window {
    public static void Okno() throws Exception {


        GLFW.glfwInit();
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        long window = GLFW.glfwCreateWindow(W, H, "Collision", 0, 0);
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


        String[] MazeString = Maze.split("\n");
        String[][] pos3nsfwString = new String[MazeString.length][3];

        for (int i = 0; i < MazeString.length; i++) {
            pos3nsfwString[i] = MazeString[i].split(";");
        }

        for (int i = 0; i < pos3nsfwString.length; i++) {
            for (int j = 0; j < 3; j++) {
                pos3nsfwString[i][j] += 'f';
            }
        }

        for (int i = 0; i < 12; i++) {
            float[] tempPos = new float[12];
            tempPos[0] = Float.parseFloat(pos3nsfwString[i][0]); // x1
            tempPos[1] = Float.parseFloat(pos3nsfwString[i][1]); // y1
            tempPos[2] = 0.0f; // z1

            tempPos[3] = Float.parseFloat(pos3nsfwString[i][0]) + Float.parseFloat(pos3nsfwString[i][2]); // x2
            tempPos[4] = Float.parseFloat(pos3nsfwString[i][1]); // x2
            tempPos[5] = 0.0f; // z2

            tempPos[6] = Float.parseFloat(pos3nsfwString[i][0]) + Float.parseFloat(pos3nsfwString[i][2]); // x3
            tempPos[7] = Float.parseFloat(pos3nsfwString[i][1]) + Float.parseFloat(pos3nsfwString[i][2]); // y3
            tempPos[8] = 0.0f; // z3

            tempPos[9] = Float.parseFloat(pos3nsfwString[i][0]); // x4
            tempPos[10] = Float.parseFloat(pos3nsfwString[i][1]) + Float.parseFloat(pos3nsfwString[i][2]); // y4
            tempPos[11] = 0.0f; // z4


            Square sqr = new Square();
            sqr.setedges(tempPos);
            pozice.add(sqr.getSquareVaoId());
        }

        Game.init();
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Key input management
            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS)
                GLFW.glfwSetWindowShouldClose(window, true); // Send a shutdown signal...

            // Change the background color
            GL33.glClearColor(0f, 0f, 0f, 1f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            if (collide == true) {
                Game.CollisionRender();
                Game.update(window);
            }
            else {
                Game.render();
                Game.update(window);
            }

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
