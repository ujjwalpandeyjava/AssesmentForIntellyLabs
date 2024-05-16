# How to run?

#### `npm i`
It will install all the dependencies i have installed,\
Prerequisites: nodejs installed in the system.

### Start provided backend



#### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

#### `npm run build`

Builds the app for production to the `build` folder.\
Creates optimized build for the best performance.\
Your app is ready to be deployed!


---
# Test Cases 
	Enter you incorrect and correct registration details for registration/login to check how it works.

	1. Authorization
	2. Authentication
	3. Side menu bar
	3. Page navigation

I haven't added session management, with cookies, local storage, etc. it can be used as: when a user login we save use auth details in any of those and check the existence of the use session with chosen option.\
For this example I have used the useContext as alternative. to show global state management.