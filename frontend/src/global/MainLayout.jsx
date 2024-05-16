import { useContext } from 'react';
import { Outlet, useNavigate } from 'react-router'
import { Link } from 'react-router-dom';
import { Bounce, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import UserAuthContext from './UserAuthProvider';

function MainLayout() {
  const nav = new useNavigate();
  const { userAuthDetails, setUserAuthDetails } = useContext(UserAuthContext);
  function logout(e) {
    e.preventDefault();
    let clone = structuredClone(userAuthDetails);
    clone.isLoggedIn = false;
    clone.email = '';
    clone.userType = "";
    setUserAuthDetails(clone)
    nav("/login")
  }
  return (
    <>
      <header className="header">
        <div className="container">
          <Link to="/" className="logo">Company Logo</Link>
          <nav className="navigation">
            {userAuthDetails.isLoggedIn === false ? <>
              <Link to="/login"><div>Sign in</div></Link>
              <Link to="/register"><div>Sign up</div></Link>
            </> : <>
              <Link to="/app"><div>Home</div></Link>
              <Link to="" onClick={logout}><div>Logout</div></Link>
            </>
            }
          </nav>
        </div>
      </header>
      <div className='rootBody'>
        <Outlet />
        <ToastContainer
          position="bottom-right"
          autoClose={7003}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick={true}
          rtl={false}
          pauseOnFocusLoss
          draggable={true}
          pauseOnHover={true}
          theme="light"
          transition={Bounce}
        />
      </div>
    </>


  )
}

export default MainLayout;