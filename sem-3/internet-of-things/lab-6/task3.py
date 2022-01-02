import RPi.GPIO as GPIO
from time import sleep
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
button = 23
led = 24
GPIO.setup(button,GPIO.IN,pull_up_down=GPIO.PUD_UP)
GPIO.setup(led,GPIO.OUT)
while True:
	button_state = GPIO.input(button)
	if button_state == 0:
		GPIO.output(led,GPIO.HIGH)
	else:
		GPIO.output(led,GPIO.LOW)
	sleep(1)
