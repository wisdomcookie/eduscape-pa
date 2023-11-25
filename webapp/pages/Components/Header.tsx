import React from 'react';
import Link from 'next/link';

const Header: React.FC = () => {
  return (
    <header style={{ backgroundColor: '#FFFADD', padding: '10px', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
      <div className="buttons" style={{ display: 'flex', alignItems: 'center', gap: '20px' }}>
      <div className="logo">
      <Link href="./" style={{ textDecoration: 'none' }}>
        <img src="./edu_logo.png" alt="Logo" style={{ maxHeight: '75px' }} />
        </Link>
      </div>
      <div className="buttons" style={{ display: 'flex', alignItems: 'center', gap: '20px',  marginLeft: 'auto', paddingLeft: '100px' }}>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Oleo+Script:wght@400&display=swap" />
        <Link href="./top25" style={{ textDecoration: 'none' }}>
          <button className="button" style={{ fontSize: '26px', color: '#96D691', fontFamily: 'Oleo Script, cursive' }}>Top 25</button>
        </Link>
        <Link href="./bottom25" style={{ textDecoration: 'none' }}>
          <button className="button" style={{ fontSize: '26px', color: '#96D691', fontFamily: 'Oleo Script, cursive' }}>Bottom 25</button>
        </Link>
        <Link href="./searchScreen" style={{ textDecoration: 'none' }}>
          <button className="button" style={{ fontSize: '26px', color: '#96D691', fontFamily: 'Oleo Script, cursive' }}>Search and Compare</button>
        </Link>
        </div>
      </div>
    </header>
  );
};

export default Header;
