import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="Home">
      Home \
      <li><Link to="." relative='route'>back-route</Link></li>

    </div>
  );
}

export default Home;
