import { Entity, PrimaryGeneratedColumn, Column, JoinColumn, OneToOne } from 'typeorm';
import { Organization } from './Organization';

@Entity()
export class Category {
  @PrimaryGeneratedColumn()
  id!: number;

  @OneToOne(type => Organization)
  @JoinColumn()
  organization!: Organization;

  @Column({ type: 'varchar', length: 50 })
  title!: string;

  @Column({ type: 'json', nullable: true })
  role!: JSON;
}
