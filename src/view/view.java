package view;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.ListPratos;
import model.Prato;

/**
 *
 * @author jonat_000
 */
public class view extends javax.swing.JFrame {
    
    //MODEL
    private final Prato massa = new Prato("Lasanha","");
    private final Prato naoMassa = new Prato("Bolo de Chocolate","");
    
    //LISTS
    private final ListPratos pratosMassa = new ListPratos();
    private final ListPratos pratosNaoMassa = new ListPratos();
    
    //VARIAVEIS GLOBAIS
    private int resposta;

    /**
     * Creates new form view
     */
    public view() {        
        initComponents();
                      
        /*Adicionando os pratos iniciais do jogo*/
        this.pratosMassa.getPratos().add(massa);
        this.pratosNaoMassa.getPratos().add(naoMassa);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jBtOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jogo Gourmet");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Pense em um prato que gosta");

        jBtOk.setText("OK");
        jBtOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jBtOk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtOk)
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtOkActionPerformed
        /*Ocultando a view*/
        this.setVisible(false);
        
        /*Iniciando o jogo*/
        initJogo();
        
        /*Apresentando a view novamente*/
        this.setVisible(true);
    }//GEN-LAST:event_jBtOkActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String ... args) {
        /*Atribuindo ao jogo o LookAndFeel nativo do sistema operacional instalado na máquina*/
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Erro ao carregar LookAndFeel");
        }

        /*Intânciando a view e apresentando a mesma*/
        java.awt.EventQueue.invokeLater(() -> {
            new view().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtOk;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    /*Método responsável por iniciar o jogo
    * sempre que o usuário clicar no botão OK*/
    private void initJogo() {
        resposta = JOptionPane.showConfirmDialog(rootPane, "O prato que você pensou é massa ?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            /*Caso o usuário escolha a opção Massa (Sim)*/
            advinharPratos(pratosMassa);
            return;
        }
        
        /*Caso o usuário não escolha a opção Massa (não)*/
        advinharPratos(pratosNaoMassa);        
    }
    
    /*Método responsável por adivinhar o prato em que o usuário pensou*/
    private void advinharPratos(ListPratos pratos) {
        int contador;
        int tamanhoList = pratos.getPratos().size() - 1;

        /*Percorro a lista perguntando sobre a característica do prato pensado*/
        for (contador = tamanhoList; contador > 0; contador--) {
            resposta = perguntaPrato(pratos, contador, true);

            /*Caso a caractristica seja confirmada pergunto sobre o prato
            * ao qual a característica pertence*/
            if (resposta == JOptionPane.YES_OPTION) {
                
                resposta = perguntaPrato(pratos, contador, false);
                
                /*Se o prato apresentado for o que o usuário pensou apresento a 
                * mensagem de que acertei o prato que o mesmo pensou.
                * Caso os pratos apresentados não forem o que o usuário pensou
                * então é realizado o cadastro do novo prato pensado informando
                * a descrição e característica do prato. exemplo: Pizza/Grande.*/
                if (resposta == JOptionPane.YES_OPTION) {
                    
                    acertei();
                    break;                    
                } else if ((resposta == JOptionPane.NO_OPTION) && (contador == 0)) {
                    
                    adicionarPrato(pratos, contador);
                    break;                    
                }
            }
        }

        /*Se nenhuma condição dentro do laço for atendida eu pergunto sobre o prato
        * pré cadastrado para iniciar o jogo (Lasanha ou Bolo de Chocolate), se o usuário
        * confirmar então apresento a mensagem de que acertei o prato que o mesmo pensou,
        * caso contrário é realizado o cadastro do novo prato pensado.
        */
        if (contador == 0) {
            
            resposta = perguntaPrato(pratos, contador, false);
            
            if (resposta == JOptionPane.YES_OPTION) {
                
                acertei();
                return;
                
            }
            
            adicionarPrato(pratos, contador);            
        }
    }
    
    /*Método responsável por adicionar os pratos na lista das Massas e Não Massas*/
    private void adicionarPrato(ListPratos pratos, int ordemPrato) {                
        pratos.getPratos().add(montaObjetoPratoNovo(pratos, ordemPrato));
    }
    
    /*Método responsável por apresentar a mensagem de acerto sempre que o prato pensado for advinhado*/
    private void acertei() {
        JOptionPane.showMessageDialog(rootPane, "Acertei de novo!", "Acertei", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /*Método responsável por perguntar sobre os pratos pela característica ou pela descrição*/
    private int perguntaPrato(ListPratos pratos, int contador, boolean caracteristica) {
        if (caracteristica) {
            return JOptionPane.showConfirmDialog(rootPane, "O prato que pensou é ".concat(pratos.getPratos().get(contador).getCaracteristica()).concat(" ?"), "Confirm", JOptionPane.YES_NO_OPTION);
        }
        
        return JOptionPane.showConfirmDialog(rootPane, "O prato que pensou é ".concat(pratos.getPratos().get(contador).getDescricao()).concat(" ?"), "Confirm", JOptionPane.YES_NO_OPTION);
    }
    
    /*Método responsável por montar o objeto de prato que logo após será adicionado na lista de Massas ou Não Massas*/
    private Prato montaObjetoPratoNovo(ListPratos pratos, int ordemPrato) {
        String descricaoPrato = JOptionPane.showInputDialog(rootPane, "Qual prato você pensou ?", "Desisto", JOptionPane.QUESTION_MESSAGE);
        String caracteristicaPrato = JOptionPane.showInputDialog(rootPane, descricaoPrato.concat(" é ________ mas ").concat(pratos.getPratos().get(ordemPrato).getDescricao()).concat(" não."), "Complete", JOptionPane.QUESTION_MESSAGE);
        
        Prato prato = new Prato(descricaoPrato, caracteristicaPrato);
                
        return prato;        
    }
}
