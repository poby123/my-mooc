import { Entity, PrimaryGeneratedColumn, Column, JoinColumn, OneToOne, ManyToOne, OneToMany, ManyToMany } from 'typeorm';
import { Board } from './Board';
import { Member } from './Member';
import { Organization } from './Organization';

@Entity()
export class Category {
  @PrimaryGeneratedColumn()
  id!: number;

  @ManyToOne(() => Organization, organization => organization.category)
  organization!: Organization;

  @OneToMany(() => Board, board => board.category)
  board!: Board[]

  @Column({ type: 'varchar', length: 50 })
  title!: string;

  @ManyToMany(() => Member, member => member.category)
  member!: Member[];

}
