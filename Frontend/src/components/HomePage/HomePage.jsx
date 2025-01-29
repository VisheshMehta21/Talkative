import React from 'react'

const HomePage = () => {
    return (
      <div className="h-screen flex flex-col">
        <div className="h-[35%] bg-blue-300 flex items-center justify-center">
          First Div (35%)
        </div>
        <div className="h-[65%] bg-red-300 flex items-center justify-center">
          Second Div (65%)
        </div>
      </div>
    );
  };
  
  export default HomePage;
  
  