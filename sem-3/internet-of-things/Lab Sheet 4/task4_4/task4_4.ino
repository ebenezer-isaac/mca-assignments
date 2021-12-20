int count = 0;
void setup() {
  pinMode(9, OUTPUT);//RED
  pinMode(11, OUTPUT);//YELLOW
  pinMode(13, OUTPUT);//GREEN
}

void loop() {
  if (count < 101) {
    digitalWrite(9, LOW);
    digitalWrite(11, LOW);
    digitalWrite(13, HIGH);
    delay(10);
  } else if (count < 201) {
    digitalWrite(9, LOW);
    digitalWrite(11, HIGH);
    digitalWrite(13, LOW);
    delay(10);
  } else if (count < 301) {
    digitalWrite(9, HIGH);
    digitalWrite(11, LOW);
    digitalWrite(13, LOW);
    delay(10);
  } else {
    count = -1;
  }
  count++;
}
