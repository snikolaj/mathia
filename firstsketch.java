float r = 0;
float a = 0;

float x_start = 200;
float y_start = 200;

final float maxDist = 3000;

class Rectangle{
  float cx;
  float cy;
  float h;
  float w;
}

class Point{
  float px;
  float py;
}

float euclideanDist(float x, float y, Point p){
  return sqrt((x - p.px)*(x - p.px) + (y - p.py)*(y - p.py));
}

float euclideanDist(float x, float y, float px, float py){
  return sqrt((x - px)*(x - px) + (y - py)*(y - py));
}


int rectNum = 2;

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
  
  rects[1].cx = 200;
  rects[1].cy = 150;
  rects[1].h = 50;
  rects[1].w = 50;
  
}

void draw(){
  background(204);
  
  for(int i = 0; i < rectNum; i++){
    rect(rects[i].cx, rects[i].cy, rects[i].h, rects[i].w);
  }
  
  for(a = 0; a < PI / 2; a += (PI / 2) / width){
    float dist = maxDist;
    
    
    for(int i = 0; i < rectNum; i++){
      float tempDist = lineIntersects(x_start, y_start, x_start + maxDist * cos(a + r), y_start + maxDist * sin(a + r), rects[i]);
      if(dist > tempDist){
        dist = tempDist * cos(a - PI/4); // https://stackoverflow.com/questions/66591163/how-do-i-fix-the-warped-perspective-in-my-raycaster
      }
    }
    
    line(x_start, y_start, x_start + dist * cos(a + r), y_start + dist * sin(a + r));
    if(dist != maxDist){
      stroke(dist);
      line(a * (width) / (PI / 2), 
          (height - (height * (1 - dist / maxDist))), 
          a * (width) / (PI / 2), 
          (height * (1 - dist / maxDist)));
    }
  }
}

float lineIntersects(float x1, float y1, float x2, float y2, Rectangle rectcheck){
  float t, u;
  float px, py;
  float dist = maxDist;
  
  // upper line
  t = ((y1 - rectcheck.cy)*(rectcheck.w))/((y1 - y2)*(rectcheck.w));
  u = ((x1 - rectcheck.cx)*(y1 - y2) - (y1 - rectcheck.cy)*(x1 - x2))/(-(y1 - y2)*(-rectcheck.w));

  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    if(euclideanDist(x1, y1, px, py) < dist){
      dist = euclideanDist(x1, y1, px, py);
    }  
  }

  // left line
  t = ((x1 - rectcheck.cx)*(rectcheck.h))/((x1 - x2)*(rectcheck.h));
  u = ((x1 - rectcheck.cx)*(y1 - y2) - (y1 - rectcheck.cy)*(x1 - x2))/((x1 - x2)*(-rectcheck.h));
  
  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    if(euclideanDist(x1, y1, px, py) < dist){
      dist = euclideanDist(x1, y1, px, py);
    }  
  }
  
  // bottom line
  t = ((y1 - (rectcheck.cy + rectcheck.h))*(rectcheck.w))/((y1 - y2)*(rectcheck.w));
  u = ((x1 - rectcheck.cx)*(y1 - y2) - (y1 - (rectcheck.cy + rectcheck.h))*(x1 - x2))/((y1 - y2)*(rectcheck.h));
  
  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    if(euclideanDist(x1, y1, px, py) < dist){
      dist = euclideanDist(x1, y1, px, py);
    }  
  }
  
  // right line
  t = ((x1 - (rectcheck.cx + rectcheck.w))*(rectcheck.h))/((x1 - x2)*(rectcheck.h));
  u = ((x1 - (rectcheck.cx + rectcheck.w))*(y1 - y2) - (y1 - rectcheck.cy)*(x1 - x2))/((x1 - x2)*(-rectcheck.h));
  
  if(0 <= t && t <= 1 && 0 <= u && u <= 1){
    px = (x1 + t*(x2 - x1));
    py = (y1 + t*(y2 - y1));
    if(euclideanDist(x1, y1, px, py) < dist){
      dist = euclideanDist(x1, y1, px, py);
    }  
  }
  
  return dist;
}



void keyPressed(){
    if(keyCode == RIGHT){
      r += 0.1f;
    }
    if(keyCode == LEFT){
      r -= 0.1f;
    }
    if(key == 's'){
      x_start -= 10*cos(r + PI/4);
      y_start -= 10*sin(r + PI/4);
    }
    if(key == 'w'){
      x_start += 10*cos(r + PI/4);
      y_start += 10*sin(r + PI/4);
    }   
}
