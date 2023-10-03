import React from 'react';

interface PercentileCircleProps {
  percentile: number;
}

const PercentileCircle: React.FC<PercentileCircleProps> = ({ percentile }) => {
  const lerp = (start: number, end: number, t: number) => {
    return start + t * (end - start);
  };

  const red = Math.round(lerp(255, 150, percentile / 100));
  const green = Math.round(lerp(183, 214, percentile / 100)); // Adjusted green value to be brighter
  const blue = Math.round(lerp(183, 145, percentile / 100));

  const circleStyle: React.CSSProperties = {
    width: '70px',
    height: '70px',
    borderRadius: '50%',
    background: `rgb(${red}, ${green}, ${blue})`,
    position: 'relative',
  };

  const labelStyle: React.CSSProperties = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    fontSize: '1.5rem',
    color: 'white',
  };

  return (
    <div style={circleStyle}>
      <div style={labelStyle}>{percentile}%</div>
    </div>
  );
};

export default PercentileCircle;
