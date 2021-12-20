int delayVariable = 0;
void setup(){
	pinMode(13, OUTPUT);
}
void loop(){
	digitalWrite(13, HIGH);
	delay(delayVariable);
	digitalWrite(13, LOW);
	delay(delayVariable);
	delayVariable+=10;
	if(delayVariable==2000){
		delayVariable=0;
	}
}
