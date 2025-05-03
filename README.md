Here I worked on Web Form with All types of automation things,   

************************* WEB FORM *********************

Input Text          [id="my-text-id"]
Password            //input[@type="password"]
Text Area           //textarea[@class="form-control"]
Dropdown(Select)    //select[@class="form-select"]      
Dropddown(datalist) //input[@list="my-options"]
                      id="my-check-1"
                      id="my-radio-2"

#################### Color Picker #####################

colour picker         //input[@name='my-colors']

################# Upload File #########################
Choose File           //input[@type="file"]

	//2. This is the javascriptexecutor method
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//input[@type='file']")));
	
	//File Path Selection, Copying file path into clipboard ctrl + c
		StringSelection filePathSelection = new StringSelection("C:\\Users\\appun\\Downloads\\src.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
		Thread.sleep(3000);

	//Robot Class, Ctrl + V
		Robot rm =new Robot();
		rm.keyPress(KeyEvent.VK_CONTROL);
		rm.keyPress(KeyEvent.VK_V);
		rm.keyRelease(KeyEvent.VK_V);
		rm.keyRelease(KeyEvent.VK_CONTROL);
		
		//Now to Click on Enter Button
		rm.keyPress(KeyEvent.VK_ENTER);


##################### CALENDER DATE SELECT ############
Click on Calender     //input[@name="my-date"]

Calender div full body   //div[@class="datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom"]

Month Selection      className("datepicker-switch")
Date Select          //td[@class='day'][normalize-space()='1']


################### RANGE SELECTION ##################### 

Range selection            //input[@class="form-range"]


###################### Submit Button ####################
submit button              //button[@type='submit']
