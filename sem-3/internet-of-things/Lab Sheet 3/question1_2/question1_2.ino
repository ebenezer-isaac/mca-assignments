void setup() {
  pinMode(13, OUTPUT);
  pinMode(11, OUTPUT);
  pinMode(9, OUTPUT);
}
void loop() {
  digitalWrite(13, HIGH);
  digitalWrite(11, HIGH);
  digitalWrite(9, HIGH);
  delay(100); 
  digitalWrite(13, LOW); 
  digitalWrite(11, LOW); 
  digitalWrite(9, LOW);   
  delay(100); 
  digitalWrite(13, HIGH);
  digitalWrite(11, HIGH);
  digitalWrite(9, HIGH);
  delay(100);                     
  digitalWrite(13, LOW); 
  digitalWrite(11, LOW); 
  digitalWrite(9, LOW);   
  delay(800);                     
}
