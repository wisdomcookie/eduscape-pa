import React from 'react';
import PercentileCircle from './PercentileCirlce';
import PercentileCircleNull from './nullPercentileCircle';

const KeyRow: React.FC = () => {
  const containerStyle: React.CSSProperties = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    padding: '20px',
  };

  const titleStyle: React.CSSProperties = {
    fontSize: '1.5rem',
    fontWeight: 'bold',
    marginBottom: '10px',
  };

  const tableContainerStyle: React.CSSProperties = {
    display: 'flex',
    justifyContent: 'center',
    width: '100%',
  };

  const tableStyle: React.CSSProperties = {
    display: 'flex',
    justifyContent: 'center',
  };

  const labelStyle: React.CSSProperties = {
    fontSize: '1.2rem',
    justifyContent: 'center',
  };

  return (
    <div style={containerStyle}>
      <div style={titleStyle}>Key for Percentiles in Relation to All Schools</div>
      <div style={tableContainerStyle}>
        <div style={tableStyle}>
        <div style={{  textAlign: 'center',
    alignContent: 'center',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',}}>
            <p style={labelStyle}>Unreported</p>
            <PercentileCircleNull percentile={-1}  color = "rgb(255, 152, 221)" />
          </div>
          <div style={{ textAlign: 'center' }}>
            <p style={labelStyle}>0%</p>
            <PercentileCircle percentile={0} />
          </div>
          <div style={{ textAlign: 'center' }}>
            <p style={labelStyle}>20%</p>
            <PercentileCircle percentile={20} />
          </div>
          <div style={{ textAlign: 'center' }}>
            <p style={labelStyle}>40%</p>
            <PercentileCircle percentile={40} />
          </div>
          <div style={{ textAlign: 'center' }}>
            <p style={labelStyle}>60%</p>
            <PercentileCircle percentile={60} />
          </div>
          <div style={{ textAlign: 'center' }}>
            <p style={labelStyle}>80%</p>
            <PercentileCircle percentile={80} />
          </div>
          <div style={{ textAlign: 'center' }}>
            <p style={labelStyle}>99%</p>
            <PercentileCircle percentile={100} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default KeyRow;
