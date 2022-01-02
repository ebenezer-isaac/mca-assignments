import RPi.GPIO as GPIO
import json, time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(18,GPIO.OUT)
GPIO.output(18,GPIO.LOW)
morse = {}
unit = 0.250
with open("morse-code.json") as json_file:
	morse = json.load(json_file)
text = "Ebenezer"
text = text.lower()
print("Morse Code for :",text)
for letter in text:
	if letter==' ':
		sleep(unit*7)
	code = morse[letter]
	print(letter,":",code)
	for part in code:
		if part=='.':
			GPIO.output(18,GPIO.HIGH)
			time.sleep(unit)
		elif part=='-':
			GPIO.output(18,GPIO.HIGH)
			time.sleep(unit*3)
		GPIO.output(18,GPIO.LOW)
		time.sleep(unit)
	GPIO.output(18,GPIO.LOW)
	time.sleep(unit*3)
