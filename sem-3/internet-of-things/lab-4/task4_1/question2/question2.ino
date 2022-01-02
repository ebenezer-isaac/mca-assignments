int red = 9;
int yellow = 11;
int green = 13;
void setup() {
  pinMode(red, OUTPUT);//RED
  pinMode(yellow, OUTPUT);//YELLOW
  pinMode(green, OUTPUT);//GREEN
}
void loop() {

  digitalWrite(red, HIGH);
  digitalWrite(yellow, LOW);
  digitalWrite(green, LOW);
  delay(5000); 
  
  digitalWrite(red, LOW); 
  digitalWrite(yellow, HIGH); 
  digitalWrite(green, LOW);   
  delay(2000); 
  
  digitalWrite(red, LOW);
  digitalWrite(yellow, LOW);
  digitalWrite(green, HIGH);
  delay(5000); 
}
