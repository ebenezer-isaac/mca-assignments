void setup() {
  pinMode(9, OUTPUT);//RED
  pinMode(11, OUTPUT);//YELLOW
  pinMode(13, OUTPUT);//GREEN
}
void loop() {

  digitalWrite(9, HIGH);
  digitalWrite(11, LOW);
  digitalWrite(13, LOW);
  delay(5000); 
  
  digitalWrite(9, LOW); 
  digitalWrite(11, HIGH); 
  digitalWrite(13, LOW);   
  delay(2000); 
  
  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, HIGH);
  delay(5000); 
}
