import React, { useState, useEffect } from "react";
import { Users, Compass, Bookmark, Hash, User } from "lucide-react";
import { motion, AnimatePresence } from "framer-motion";
import Header from "../components/Header";
import { useAuth } from "../context/auth/useAuth";

const suggestedUsers = [
  {
    id: 1,
    name: "Emma Wilson",
    bio: "UI/UX Designer",
    skills: ["Design", "Figma"],
  },
  {
    id: 2,
    name: "Michael Chen",
    bio: "Full Stack Developer",
    skills: ["React", "Node.js"],
  },
  {
    id: 3,
    name: "Sarah Johnson",
    bio: "Data Scientist",
    skills: ["Python", "ML"],
  },
];

const trendingTopics = [
  { id: 1, name: "React Hooks", count: 342 },
  { id: 2, name: "CSS Grid", count: 275 },
  { id: 3, name: "UX Design", count: 189 },
  { id: 4, name: "Python", count: 156 },
];

const MainLayout = ({ children, activeTab }) => {
  const [isLoaded, setIsLoaded] = useState(false);
  const { currentUser } = useAuth();

  useEffect(() => {
    if (!currentUser) {
      setIsLoaded(false);
    } else {
      setIsLoaded(true);
    }
  }, [currentUser]);

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100">
      {/* header */}
      <Header activeTab={activeTab} />

      {/* main content with side columns */}
      <div className="pt-20 pb-10 px-4">
        <div className="max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-12 gap-6">
          {/* left side bar */}
          <motion.div
            className="hidden lg:block lg:col-span-3 space-y-4"
            initial={{ opacity: 0, x: -20 }}
            animate={{ opacity: isLoaded ? 1 : 0, x: isLoaded ? 0 : -20 }}
            transition={{ duration: 0.5 }}
          >
            
           

            
          </motion.div>

          {/* main content */}
          <motion.div
            className="col-span-1 lg:col-span-6 space-y-6"
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: isLoaded ? 1 : 0, y: isLoaded ? 0 : 20 }}
            transition={{ duration: 0.5, delay: 0.2 }}
          >
            {/* here will render the specific tab content */}
            {children}
          </motion.div>

          {/* right side bar */}
          <motion.div
            className="hidden lg:block lg:col-span-3 space-y-4"
            initial={{ opacity: 0, x: 20 }}
            animate={{ opacity: isLoaded ? 1 : 0, x: isLoaded ? 0 : 20 }}
            transition={{ duration: 0.5, delay: 0.4 }}
          >
            
           
          </motion.div>
        </div>
      </div>
    </div>
  );
};

export default MainLayout;
