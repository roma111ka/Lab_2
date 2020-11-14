import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.lang.Math.*;

public class MainFrame1 extends JFrame {
    private static final int WIDTH =900;//размеры окон
    private static final int HEIGHT=600;
    private static Double sum =0.0;
    private JTextField textFieldX;// текстовые поля для считывания значения переменных
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult; // текстовые поля для отображения результата
    private JTextField textFieldResultSum;
    private JLabel imageType;
    private ButtonGroup radioButtons =new ButtonGroup();//радиокнопки
    private Box hboxFormulaType=Box.createHorizontalBox(); //контейнер для отображения радиокнопок
    private ButtonGroup radioMemoryButtons = new ButtonGroup();
    private Box hBoxMemoryType = Box.createHorizontalBox();
    private JTextField memoryTextField;
    private int formulaID=1;
    private int memoryId= 1;
    private Double mem1 = (double) 0;
    private Double mem2 = (double) 0;
    private Double mem3 = (double) 0;

    //Формула №1
    private double formula1(Double x, Double y, Double z)
    {
        if (y <= 0)	{
            JOptionPane.showMessageDialog(MainFrame1.this,
                    "У должен быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if ((x*x+ sin(z)+exp(cos(z))) < 0)	{
            JOptionPane.showMessageDialog(MainFrame1.this,
                    "Выражение под корнем должно быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        return sin(log(y)+ sin((Math.PI)*y*y))* pow(pow(x,2)+ sin(z)+exp(cos(z)),1/4);
    }
    //Формула №2
    private double formula2(Double x, Double y, Double z)
    {
        if (y == -1)	{
            JOptionPane.showMessageDialog(MainFrame1.this,
                    "У должен не ровняться -1", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if (x <= 0)	{
            JOptionPane.showMessageDialog(MainFrame1.this,
                    "Х должен быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        if ((exp(cos(x))+pow(sin(Math.PI*z),2)) < 0)	{
            JOptionPane.showMessageDialog(MainFrame1.this,
                    "Выражение под корнем должно быть положительным", "" +
                            "Ошибка ввода", JOptionPane.WARNING_MESSAGE);
            return 0;
        }
        return pow(cos(exp(x))+log(pow(1+y,2))+pow(exp(cos(x))+pow(sin(Math.PI*z),2),1/2)+pow(1/x,1/2)+cos(pow(y,2)),sin(z));
    }
//метод для добавления кнопок на панель
    private void addRadioButtons(String buttonName,final int formulaID)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame1.this.formulaID=formulaID;
                if (formulaID==1)
                    imageType.setIcon(new ImageIcon(MainFrame1.class.getResource("f1.png")));
                if (formulaID==2)
                    imageType.setIcon(new ImageIcon(MainFrame1.class.getResource("f2.png")));

            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    private void addMemoryRadioButton (String buttonName, final int memoryId)	{
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)	{
                MainFrame1.this.memoryId = memoryId;
                if (memoryId == 1)	memoryTextField.setText(mem1.toString());
                if (memoryId == 2)	memoryTextField.setText(mem2.toString());
                if (memoryId == 3)	memoryTextField.setText(mem3.toString());
            }
        });

        radioMemoryButtons.add(button);
        hBoxMemoryType.add(button);
    }
    public MainFrame1()
    {
        super("Вычисление формулы");
        Toolkit kit = Toolkit.getDefaultToolkit();
        setSize(WIDTH, HEIGHT);
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

       hboxFormulaType.add(Box.createHorizontalGlue()); //область с выбором формул
       addRadioButtons("Формула 1",1);
       addRadioButtons("Формула 2",2);
       radioButtons.setSelected( radioButtons.getElements().nextElement().getModel(),true);
       hboxFormulaType.add(Box.createHorizontalGlue());
       hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Box picture = Box.createHorizontalBox();                    // область с картинкой
        picture.add(Box.createVerticalGlue());
        picture.add(Box.createHorizontalGlue());
        imageType = new JLabel(new ImageIcon(MainFrame1.class.getResource("f1.png")));
        picture.add(imageType);
        picture.add(Box.createHorizontalGlue());
        picture.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        //Область с полями ввода X, Y, Z
        JLabel labelForX=new JLabel("X:");
        textFieldX=new JTextField("0",10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY=new JLabel("Y:");
        textFieldY=new JTextField("0",10);
        textFieldY.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForZ=new JLabel("Z:");
        textFieldZ=new JTextField("0",10);
        textFieldZ.setMaximumSize(textFieldX.getPreferredSize());

        Box hboxVariables= Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.black));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult=new JLabel("Результат");
        textFieldResult = new JTextField("", 15);
        textFieldResult.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());


        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());

                    Double result;
                    if (formulaID == 1) {
                        result = formula1(x, y, z);
                    } else {
                        result = formula2(x, y, z);
                    }

                   textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame1.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });

        hBoxMemoryType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("Память 1", 1);
        addMemoryRadioButton("Память 2", 2);
        addMemoryRadioButton("Память 3", 3);
        // по умолчанию на первую память
        radioMemoryButtons.setSelected(radioMemoryButtons.getElements().nextElement().getModel(), true);
        //Добавить «клей» C1-H2 с правой стороны
        hBoxMemoryType.add(Box.createHorizontalGlue());
        //Задать рамку для коробки с помощью класса BorderFactory
        hBoxMemoryType.setBorder(BorderFactory.createLineBorder(Color.black ));

//Создать кнопку «Очистить поля»
        JButton buttonReset = new JButton("Очистить поля");
// Определить и зарегистрировать обработчик нажатия на кнопку
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0.0");
                textFieldY.setText("0.0");
                textFieldZ.setText("0.0");
                textFieldResult.setText("0.0");
                JOptionPane.showMessageDialog(MainFrame1.this, "Поля отчищены!","Очистка", JOptionPane.PLAIN_MESSAGE);
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener()	{
            public void actionPerformed(ActionEvent event) {

                if (memoryId == 1)	mem1 = (double) 0;
                if (memoryId == 2)	mem2 = (double) 0;
                if (memoryId == 3)	mem3 = (double) 0;
                memoryTextField.setText("0.0");
            }
        });

        memoryTextField = new JTextField("0.0", 15);
        memoryTextField.setMaximumSize(memoryTextField.getPreferredSize());

        Box hBoxMemoryField = Box.createHorizontalBox();
        hBoxMemoryField.add(Box.createHorizontalGlue());
        hBoxMemoryField.add(memoryTextField);
        hBoxMemoryField.add(Box.createHorizontalGlue());


        JButton buttonMp = new JButton("M+");
        buttonMp.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                try{
                    Double result = Double.parseDouble(textFieldResult.getText());

                    if (memoryId == 1) 	{mem1 += result;memoryTextField.setText(mem1.toString());}
                    if (memoryId == 2)	{mem2 += result;memoryTextField.setText(mem2.toString());}
                    if (memoryId == 3)	{mem3 += result;memoryTextField.setText(mem3.toString());}

                }catch (NumberFormatException ex)
                { JOptionPane.showMessageDialog(MainFrame1.this,
                        "Ошибка в формате записи числа с плавающей точкой", "" +
                                "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Box hBoxControlButtons = Box.createHorizontalBox();
        hBoxControlButtons.add(Box.createHorizontalGlue());
        hBoxControlButtons.add(buttonMC);
        hBoxControlButtons.add(Box.createHorizontalStrut(30));
        hBoxControlButtons.add(buttonMp);
        hBoxControlButtons.add(Box.createHorizontalGlue());

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.red));


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(imageType);
        contentBox.add(hboxVariables);
//Добавить контейнер с результатом вычислений
        contentBox.add(hboxResult);
//Добавить контейнер с кнопками
        contentBox.add(hboxButtons);
// Добавить контейнер с выбором памяти
        contentBox.add(hBoxMemoryType);
//Добавить резултат
        contentBox.add(hBoxControlButtons);
/// поле
        contentBox.add(hBoxMemoryField);
//Добавить «клей» V2 снизу
        contentBox.add(Box.createVerticalGlue());
//Установить «вертикальную коробку» в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        MainFrame1 frame =new MainFrame1();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
