package cz.educanet;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Square {
    private float[] edges = new float[12];
    private final int[] indexs =
            {
                    0, 1, 3,
                    1, 2, 3};

    private int squareVaoId;
    private int squareVboId;
    private int squareEboId;

    private FloatBuffer fb = BufferUtils.createFloatBuffer(edges.length);
    private IntBuffer ib = BufferUtils.createIntBuffer(indexs.length);


    public Square() {
        squareVaoId = GL33.glGenVertexArrays();
        squareVboId = GL33.glGenBuffers();
        squareEboId = GL33.glGenBuffers();

        GL33.glBindVertexArray(squareVaoId);
        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, squareEboId);
        ib = ib.put(indexs).flip();
        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, ib, GL33.GL_STATIC_DRAW);
    }

    public float[] getedges() {
        return edges;
    }

    public void setedges(float[] edges) {

        this.edges = edges;
        GL33.glBindVertexArray(squareVaoId);
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, squareVboId);
        fb = fb.clear().put(edges).flip();
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, 0, 0);
        GL33.glEnableVertexAttribArray(0);
    }

    public int getSquareVaoId() {
        return squareVaoId;
    }

    public void setSquareVaoId(int squareVaoId) {
        this.squareVaoId = squareVaoId;
    }

    public int getindexs() {
        return indexs.length;
    }
}
