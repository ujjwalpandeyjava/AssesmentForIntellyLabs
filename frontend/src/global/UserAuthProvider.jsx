import { createContext, useState } from 'react';

const UserAuthContext = createContext();

export const UserAuthProvider = ({ children }) => {
	const [userAuthDetails, setUserAuthDetails] = useState({
		name: '',
		email: '',
		userType: '',
		isLoggedIn: false
	});

	return (
		<UserAuthContext.Provider value={{ userAuthDetails, setUserAuthDetails }}>
			{children}
		</UserAuthContext.Provider>
	);
};

export default UserAuthContext;
