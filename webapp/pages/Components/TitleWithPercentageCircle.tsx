import React from 'react';
import PercentileCircle from './PercentileCirlce';


interface TitleWithPercentileCircleProps {
  title: string;
  percentile: number;
}

const TitleWithPercentileCircle: React.FC<TitleWithPercentileCircleProps> = ({ title, percentile }) => {
  return (
    <div style={{ textAlign: 'center', margin: '8px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <div style={{ textAlign: 'center', marginBottom: '8px' }}>
        {title}
      </div>
      <PercentileCircle percentile={percentile} />
    </div>
  );
};

export default TitleWithPercentileCircle;
