import Link from 'next/link';
import React from 'react';

const HomeScreen: React.FC = () => {
  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100vh' }}>
      <div style={{ fontSize: '60px', fontWeight: 'bold', marginBottom: '10px' }}>Eduscape-PA</div>
      <div style={{ fontSize: '18px', color: '#555', textAlign: 'center', marginBottom: '30px' }}>Helping Colleges View the Full Context</div>
      <div style={{ display: 'flex', gap: '10px' }}>
      <Link href="./top25" style={{ textDecoration: 'none' }}> 
        <button style={{ padding: '15px 20px', borderRadius: '10px', fontSize: '36px', backgroundColor: '#8ECDDD', color: 'white', cursor: 'pointer', transition: 'background-color 0.3s' }}>Top 25 Schools</button>
        </Link>
        <Link href="./bottom25" style={{ textDecoration: 'none' }}> 
        <button style={{ padding: '15px 20px', borderRadius: '10px', fontSize: '36px', backgroundColor: '#8ECDDD', color: 'white', cursor: 'pointer', transition: 'background-color 0.3s' }}>Bottom 25 Schools</button>
        </Link>
        <Link href="./searchScreen" style={{ textDecoration: 'none' }}> 
        <button style={{ padding: '15px 20px', borderRadius: '10px', fontSize: '36px', backgroundColor: '#8ECDDD', color: 'white', cursor: 'pointer', transition: 'background-color 0.3s' }}>Search and Compare</button>
        </Link>
      </div>
    </div>
  );
};

export default HomeScreen;
