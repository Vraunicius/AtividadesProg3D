#version 330

in vec3 aPosition;
in vec3 aColor;

uniform mat4 uWorld;
uniform mat4 uProjection;
uniform mat4 uView;

out vec3 vColor;

void main()
{
    vec4 WorldPos = uWorld * vec4(aPosition,1.0);
    gl_Position = uProjection * uView * WorldPos;
    vColor = aColor;
}
