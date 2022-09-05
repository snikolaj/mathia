float r = 0;
float a = 0;

float x_start = 200;
float y_start = 200;

class Rectangle{
  float cx;
  float cy;
  float h;
  float w;
}

int rectNum = 1;

Rectangle rects[]= new Rectangle[rectNum];

void setup(){
  size(640, 480);  
  
  for(int i = 0; i < rectNum; i++){
    rects[i] = new Rectangle();
  }
  
  rects[0].cx = 250;
  rects[0].cy = 250;
  rects[0].h = 100;
  rects[0].w = 100;
  
  /*rects[1].cx = 250;
  rects[1].cy = 250;
  rects[1].h = 50;
  rects[1].w = 50;*/
  
}

void draw(){
  background(204);
  
  for(int i = 0; i < rectNum; i++){
    rect(rects[i].cx, rects[i].cy, rects[i].h, rects[i].w);
    lineIntersects(x_start, y_start, x_start + 100 * cos(a + r), y_start + 100 * sin(a + r), rects[i]);
  }
  
  line(x_start, y_start, x_start + 100 * cos(a + r), y_start + 100 * sin(a + r));
  
  
  /*for(a = 0; a < PI / 2; a += (PI / 2) / 90){
    line(x_start, y_start, x_start + 100 * cos(a + r), y_start + 100 * sin(a + r));
    
  }*/
}

boolean lineIntersects(float x1, float y1, float x2, float y2, Rectangle rectcheck){
  float t, u;
  float px, py;
  
  // upper line
  t = ((y1 - rectcheck.cy)*(rectcheck.w))/((y1 - y2)*(rectcheck.w));
  u = ((x1 - rectcheck.cx)*(y1 - y2) - (y1 - rectcheck.cy)*(x1 - x2))/(-(y1 - y2)*(-rectcheck.w));

  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    circle(px, py, 5);
  }

  // left line
  t = ((x1 - rectcheck.cx)*(rectcheck.h))/((x1 - x2)*(rectcheck.h));
  u = ((x1 - rectcheck.cx)*(y1 - y2) - (y1 - rectcheck.cy)*(x1 - x2))/((x1 - x2)*(-rectcheck.h));
  
  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    circle(px, py, 5);
  }
  
  // bottom line
  t = ((y1 - (rectcheck.cy + rectcheck.h))*(rectcheck.w))/((y1 - y2)*(rectcheck.w));
  u = ((x1 - rectcheck.cx)*(y1 - y2) - (y1 - (rectcheck.cy + rectcheck.h))*(x1 - x2))/((y1 - y2)*(rectcheck.h));
  
  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    circle(px, py, 5);
  }
  
  // right line
  t = ((x1 - (rectcheck.cx + rectcheck.w))*(rectcheck.h))/((x1 - x2)*(rectcheck.h));
  u = ((x1 - (rectcheck.cx + rectcheck.w))*(y1 - y2) - (y1 - rectcheck.cy)*(x1 - x2))/((x1 - x2)*(-rectcheck.h));
  
  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    circle(px, py, 5);
  }
  
  return false;
}



void keyPressed(){
    if(keyCode == RIGHT){
      r += 0.1f;
    }
    if(keyCode == LEFT){
      r -= 0.1f;
    }
    if(key == 's'){
      y_start += 10;
    }
    if(key == 'w'){
      y_start -= 10;
    }   
    if(key == 'd'){
      x_start += 10;
    }
    if(key == 'a'){
      x_start -= 10;
    }   
}
