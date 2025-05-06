import React, { useEffect } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
  useLocation,
} from "react-router-dom";

//main app pages
import SkillSharingFeed from "./Pages/SkillSharingFeed.jsx";

//auth Context
import { useAuth } from "./context/auth/useAuth.js";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import MainLayout from "./layout/MainLayout.jsx";

//scrollToTop component to reset scroll position on navigation
const ScrollToTop = () => {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return null;
};

function App() {
  const { currentUser } = useAuth(); 

  return (
    <Router>
      <ScrollToTop />
      <Routes>
        {/* auth Routes */}

        {/* protected Routes */}
        <Route element={<ProtectedRoute />}>
          {/* default route redirects to skill sharing feed */}
          <Route
            path="/"
            element={
              <MainLayout activeTab="feed">
                <SkillSharingFeed />
              </MainLayout>
            }
          />


        </Route>

        {/* fallback - redirect to home */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </Router>
  );
}

export default App;

// function App() {
//   const [count, setCount] = useState(0)

//   return (
//     <>
//       <div>
//         <a href="https://vite.dev" target="_blank">
//           <img src={viteLogo} className="logo" alt="Vite logo" />
//         </a>
//         <a href="https://react.dev" target="_blank">
//           <img src={reactLogo} className="logo react" alt="React logo" />
//         </a>
//       </div>
//       <h1>Vite + React</h1>
//       <div className="card">
//         <button onClick={() => setCount((count) => count + 1)}>
//           count is {count}
//         </button>
//         <p>
//           Edit <code>src/App.jsx</code> and save to test HMR
//         </p>
//       </div>
//       <p className="read-the-docs">
//         Click on the Vite and React logos to learn more
//       </p>
//     </>
//   )
// }

// export default App
