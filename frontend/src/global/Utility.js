export function getToHome(navigate) {
  console.log("Go to home...");
  navigate('/')
}
export const userTypes = {
  Guest: "Guest",
  AdminUser: "AdminUser",
  NormalUser: "NormalUser"
}


/*
const navigate = useNavigate();
  const goBack = () => {
    navigate(-1); // Go back one level in history
  };

  const goToBase = () => {
    navigate('/'); // Navigate to the base URL
  };

  return (
    <div>
      <button onClick={goBack}>Go Back</button>
      <button onClick={goToBase}>Go to Base URL</button>
    </div>
  );
*/