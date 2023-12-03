import React, { useEffect, useRef } from 'react';
import Chart from 'chart.js/auto';

interface BarChartProps {
  xAxisLabels: string[];
  yAxisTitle: string;
  datasets: {
    label: string;
    data: number[];
    borderColor: string;
    backgroundColor: string;
    fill?: boolean;
  }[];
}

function BarChart({ xAxisLabels, yAxisTitle, datasets }: BarChartProps) {
  const chartRef = useRef<Chart | null>(null);

  useEffect(() => {
    const canvas = document?.getElementById('myChart') as HTMLCanvasElement;
    const ctx = canvas?.getContext('2d');

    if (chartRef.current) {
      chartRef.current.destroy();
    }

    const myChart = new Chart(ctx!!, {
      type: 'bar',
      data: {
        labels: xAxisLabels,
        datasets: datasets.map((dataset) => ({
          label: dataset.label,
          data: dataset.data,
          borderColor: dataset.borderColor,
          backgroundColor: dataset.backgroundColor,
          fill: dataset.fill || false,
        })),
      },
      options: {
        scales: {
          x: {
            type: 'category',
            labels: xAxisLabels,
            title: {
              display: true,
              text: 'Category',
            },
          },
          y: {
            title: {
              display: true,
              text: yAxisTitle,
            },
          },
        },
      },
    });

    chartRef.current = myChart;
  }, [xAxisLabels, yAxisTitle, datasets]);

  return (
    <>
      {/* bar chart */}
      
      <div className="w-[1100px] h-screen flex mx-auto my-auto">
        <div className='border border-gray-400 pt-0 rounded-xl w-full h-fit my-auto shadow-xl'>
          <canvas id='myChart'></canvas>
        </div>
      </div>
    </>
  );
}

export default BarChart;
