# SurfaceDuoDispathGestureBug
SurfaceDuoDispathGestureBug

AccessibilityService.dispatchGesture does not respect the screen coordinates on right screen on single screen mode (emulator and real device). 
It works ok on dual screen mode and on left screen on single screen mode. 

Dispatching a gesture at (x=100, y=100) still clicks on the turned off left screen. Clicking at x=100+1350 works.

Video demo: https://streamable.com/z7cgub
