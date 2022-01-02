void setup() {
  pinMode(9, OUTPUT);//RED
  pinMode(11, OUTPUT);//YELLOW
  pinMode(13, OUTPUT);//GREEN
  Serial.begin(9600);
}
void loop() {
  Serial.print("\nRed");
  digitalWrite(9, HIGH);
  digitalWrite(11, LOW);
  digitalWrite(13, LOW);
  delay(5000);
  
  Serial.print("\nYellow");
  digitalWrite(9, LOW);
  digitalWrite(11, HIGH);
  digitalWrite(13, LOW);
  delay(2000);

  Serial.print("\nAdvanced Green");
  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, HIGH);
  delay(500);
  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, LOW);
  delay(500);

  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, HIGH);
  delay(500);
  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, LOW);
  delay(500);

  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, HIGH);
  delay(500);
  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, LOW);
  delay(500);
  
  Serial.print("\nGreen");
  digitalWrite(9, LOW);
  digitalWrite(11, LOW);
  digitalWrite(13, HIGH);
  delay(5000);
}
