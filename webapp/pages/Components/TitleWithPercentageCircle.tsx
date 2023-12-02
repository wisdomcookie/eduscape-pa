import React from 'react';
import PercentileCircle from './PercentileCirlce';


interface TitleWithPercentileCircleProps {
  title: string;
  percentile: number;
}
//style={{ fontSize: '1.2em', fontWeight: 'bold' }
const TitleWithPercentileCircle: React.FC<TitleWithPercentileCircleProps> = ({ title, percentile }) => {
  return (
    <div style={{ textAlign: 'center', margin: '8px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <div style={{ fontSize: '.8em', fontWeight: 'bold', textAlign: 'center', marginBottom: '8px' }}>
        {title}
      </div>
      <PercentileCircle percentile={percentile*100} />
    </div>
  );
};

export default TitleWithPercentileCircle;
